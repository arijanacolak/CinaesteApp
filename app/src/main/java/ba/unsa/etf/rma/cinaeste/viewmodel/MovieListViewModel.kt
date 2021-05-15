package ba.unsa.etf.rma.cinaeste.viewmodel

import ba.unsa.etf.rma.cinaeste.data.Movie
import ba.unsa.etf.rma.cinaeste.data.MovieRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MovieListViewModel(private val searchDone: ((movies: List<Movie>) -> Unit)?,
                         private val onError: (()->Unit)?
) {

    val scope = CoroutineScope(Job() + Dispatchers.Main)

    fun getFavoriteMovies():List<Movie>{
        return MovieRepository.getFavoriteMovies();
    }

    fun getRecentMovies():List<Movie>{
        return MovieRepository.getRecentMovies();
    }

    fun search(query: String){

        // Create a new coroutine on the UI thread
        scope.launch{

            // Make the network call and suspend execution until it finishes
            val result = MovieRepository.searchRequest(query)

            // Display result of the network request to the user
            when (result) {
                is ba.unsa.etf.rma.cinaeste.data.Result.Success<List<Movie>> -> searchDone?.invoke(result.data)
                else-> onError?.invoke()
            }
        }
    }
}
