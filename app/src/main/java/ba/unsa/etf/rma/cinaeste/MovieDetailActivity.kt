package ba.unsa.etf.rma.cinaeste

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import ba.unsa.etf.rma.cinaeste.data.Movie
import ba.unsa.etf.rma.cinaeste.view.ActorsFragment
import ba.unsa.etf.rma.cinaeste.view.SimilarFragment
import ba.unsa.etf.rma.cinaeste.viewmodel.MovieDetailViewModel
import com.bumptech.glide.Glide
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MovieDetailActivity : AppCompatActivity() {

    private var movieDetailViewModel =  MovieDetailViewModel(this@MovieDetailActivity::movieRetrieved,null,null)
    private lateinit var bottomNavigation: BottomNavigationView
    private  var movie=Movie(0,"Test","Test","Test","Test","Test","Test","Test")
    private lateinit var title : TextView
    private lateinit var overview : TextView
    private lateinit var releaseDate : TextView
    private lateinit var genre : TextView
    private lateinit var website : TextView
    private lateinit var poster : ImageView
    private lateinit var backdrop : ImageView
    private lateinit var shareButton : FloatingActionButton
    private val posterPath = "https://image.tmdb.org/t/p/w780"
    private val backdropPath = "https://image.tmdb.org/t/p/w500"

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when(item.itemId){
            R.id.actorsItem -> {
                var actorsFragment = ActorsFragment(movie.title,movie.id)
                openFragment(actorsFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.similarMItem -> {
                var similarFragment = SimilarFragment(movie.title,movie.id)
                openFragment(similarFragment)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        bottomNavigation = findViewById(R.id.detailNavigation)
        title = findViewById(R.id.movie_title)
        overview = findViewById(R.id.movie_overview)
        releaseDate = findViewById(R.id.movie_release_date)
        genre = findViewById(R.id.movie_genre)
        poster = findViewById(R.id.movie_poster)
        website = findViewById(R.id.movie_website)
        shareButton = findViewById(R.id.shareButton)
        backdrop = findViewById(R.id.movie_backdrop)

        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        website.setOnClickListener{
            showWebsite()
        }
        title.setOnClickListener{
            youtubeSearch()
        }
        shareButton.setOnClickListener{
            shareOverview()
        }
        val extras = intent.extras

        if (extras != null) {
            if (extras.containsKey("movie_title")) {
                movie = movieDetailViewModel.getMovieByTitle(extras.getString("movie_title", ""))
                populateDetails()
            }
            else if (extras.containsKey("movie_id")){
                movieDetailViewModel.getMovieDetails(extras.getLong("movie_id"))
            }
        } else {
            finish()
        }
    }


    fun movieRetrieved(movie:Movie){
        this.movie =movie;
        populateDetails()
    }
    private fun populateDetails() {
        title.text=movie.title
        releaseDate.text=movie.releaseDate
        genre.text=movie.genre
        website.text=movie.homepage
        overview.text=movie.overview
        val context: Context = poster.getContext()
        var id = 0;
        if (movie.genre!==null)
            id = context.getResources()
                    .getIdentifier(movie.genre, "drawable", context.getPackageName())
        if (id===0) id=context.getResources()
                .getIdentifier("picture1", "drawable", context.getPackageName())
        Glide.with(context)
                .load(posterPath + movie.posterPath)
                .placeholder(R.drawable.picture1)
                .error(id)
                .fallback(id)
                .into(poster);
        var backdropContext: Context = backdrop.getContext()
        Glide.with(backdropContext)
                .load(backdropPath + movie.backdropPath)
                .centerCrop()
                .placeholder(R.drawable.backdrop)
                .error(R.drawable.backdrop)
                .fallback(R.drawable.backdrop)
                .into(backdrop);
    }

    private fun showWebsite(){
        val sendIntent = Intent().apply {
            action = Intent.ACTION_VIEW
            setData(Uri.parse(movie.homepage))
        }
        if (sendIntent.resolveActivity(packageManager) != null) {
            startActivity(sendIntent)
        }
    }

    private fun youtubeSearch(){
        val intent = Intent(Intent.ACTION_SEARCH).apply {
            setPackage("com.google.android.youtube")
            putExtra("query", movie.title + " trailer")
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }

    private fun shareOverview(){
        val intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, movie.overview)
            type = "text/plain"
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }
    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.actorSimilarContainer, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}