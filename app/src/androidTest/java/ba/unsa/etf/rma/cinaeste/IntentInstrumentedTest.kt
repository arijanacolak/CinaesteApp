package ba.unsa.etf.rma.cinaeste

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.view.View
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.core.graphics.drawable.toBitmap
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.PositionAssertions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.*
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class IntentInstrumentedTest {
    @get:Rule
    var intentsRule: IntentsTestRule<MovieDetailActivity> = IntentsTestRule(MovieDetailActivity::class.java,false,false)

    fun withImage(@DrawableRes id: Int) = object : TypeSafeMatcher<View>(){
        override fun describeTo(description: Description) {
            description.appendText("Drawable does not contain image with id: $id")
        }

        override fun matchesSafely(item: View): Boolean {
            val context:Context = item.context
            val bitmap: Bitmap? = context.getDrawable(id)?.toBitmap()
            return item is ImageView && item.drawable.toBitmap().sameAs(bitmap)
        }

    }

    @Test
    fun testDetailActivityInstantiation(){
        val pokreniDetalje: Intent = Intent(MovieDetailActivity::javaClass.name)
        pokreniDetalje.putExtra("movie_title","Holidate")
        intentsRule.launchActivity(pokreniDetalje)
        onView(withId(R.id.movie_title)).check(matches(withText("Holidate")))
        onView(withId(R.id.movie_genre)).check(matches(withText("romance")))
        onView(withId(R.id.movie_overview)).check(matches(withSubstring("up with being single")))
        onView(withId(R.id.movie_poster)).check(matches(withImage(R.drawable.romance)))

    }

    @Test
    fun testLinksIntent(){
        val pokreniDetalje: Intent = Intent(MovieDetailActivity::javaClass.name)
        pokreniDetalje.putExtra("movie_title","Holidate")
        intentsRule.launchActivity(pokreniDetalje)
        onView(withId(R.id.movie_website)).perform(click())
        intended(hasAction(Intent.ACTION_VIEW))
    }

    //ZSR
    @Test
    fun testLayoutDetailsActivity(){
        val pokreniDetalje: Intent = Intent(MovieDetailActivity::javaClass.name)
        pokreniDetalje.putExtra("movie_title","Pulp Holidate")
        intentsRule.launchActivity(pokreniDetalje)
        onView(withId(R.id.movie_poster)).check(isCompletelyLeftOf(withId(R.id.movie_title)))
        onView(withId(R.id.movie_release_date)).check(isCompletelyBelow(withId(R.id.movie_title)))
        onView(withId(R.id.movie_release_date)).check(isCompletelyRightOf(withId(R.id.movie_poster)))
        onView(withId(R.id.movie_genre)).check(isCompletelyBelow(withId(R.id.movie_release_date)))
        onView(withId(R.id.movie_genre)).check(isLeftAlignedWith(withId(R.id.movie_release_date)))
        onView(withId(R.id.movie_website)).check(isCompletelyBelow(withId(R.id.movie_poster)))
        onView(withId(R.id.movie_overview)).check(isCompletelyBelow(withId(R.id.movie_website))).check(isLeftAlignedWith(withId(R.id.movie_website)))
    }

    @Test
    fun testYoutubeAction(){
        val pokreniDetalje: Intent = Intent(MovieDetailActivity::javaClass.name)
        pokreniDetalje.putExtra("movie_title","Holidate")
        intentsRule.launchActivity(pokreniDetalje)
        onView(withId(R.id.movie_title)).perform(click())
        intended(hasPackage("com.google.android.youtube"))
    }
}
