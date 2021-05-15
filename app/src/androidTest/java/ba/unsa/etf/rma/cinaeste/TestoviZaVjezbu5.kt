package ba.unsa.etf.rma.cinaeste

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import android.content.Intent
import ba.unsa.etf.rma.cinaeste.view.SimpleStringAdapter
import ba.unsa.etf.rma.cinaeste.viewmodel.MovieDetailViewModel
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TestoviZaVjezbu5 {
    @get:Rule
    var intentsRule: IntentsTestRule<MainActivity> = IntentsTestRule(MainActivity::class.java,false,false)
    @get:Rule
    var detailRule: IntentsTestRule<MovieDetailActivity> = IntentsTestRule(MovieDetailActivity::class.java,false,false)

    @Test
    fun testListaGlumaca(){
        val pokreniDetalje = Intent(MovieDetailActivity::javaClass.name)
        pokreniDetalje.putExtra("movie_title","Holidate")
        detailRule.launchActivity(pokreniDetalje)
        val actors = MovieDetailViewModel().getActorsByTitle("Holidate")
        for(actor in actors)
            onView(withId(R.id.listActors)).perform(RecyclerViewActions.scrollTo<SimpleStringAdapter.SimpleViewHolder>(
                withText(actor))).check(matches(isDisplayed()))
    }

    @Test
    fun testActionShare() {
        var intent = Intent()
        intent.putExtra(Intent.EXTRA_TEXT,"Test ActionShare")
        intent.action=Intent.ACTION_SEND
        intent.type="text/plain"
        intentsRule.launchActivity(intent)
        onView(withId(R.id.searchText)).check(matches(withText("Test ActionShare")))
    }
}