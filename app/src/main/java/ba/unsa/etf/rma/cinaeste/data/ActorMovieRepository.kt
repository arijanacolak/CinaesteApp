package ba.unsa.etf.rma.cinaeste.data

import ba.unsa.etf.rma.cinaeste.BuildConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import kotlin.Result

object ActorMovieRepository {

    private const val tmdb_api_key : String = BuildConfig.TMDB_API_KEY
    fun getActorMovies():Map<String,List<String>>{
        return movieActors()
    }

    suspend fun getActors(
        id: Long
    ): ba.unsa.etf.rma.cinaeste.data.Result<MutableList<String>> {
        return withContext(Dispatchers.IO) {
            val url1 = "https://api.themoviedb.org/3/movie/$id/credits?api_key=${tmdb_api_key}"
            try {
                val url = URL(url1)
                var actors:MutableList<String> = mutableListOf()
                (url.openConnection() as? HttpURLConnection)?.run {
                    val result = this.inputStream.bufferedReader().use { it.readText() }
                    val jo = JSONObject(result)
                    val items: JSONArray = jo.getJSONArray("cast")
                    for (i in 0 until items.length()) {
                        val slicni = items.getJSONObject(i)
                        val title = slicni.getString("name")
                        actors.add(title)
                        if (i == 4) break
                    }
                }
                return@withContext ba.unsa.etf.rma.cinaeste.data.Result.Success(actors);
            }
            catch (e: MalformedURLException) {
                return@withContext ba.unsa.etf.rma.cinaeste.data.Result.Error(Exception("Cannot open HttpURLConnection"))
            } catch (e: IOException) {
                return@withContext ba.unsa.etf.rma.cinaeste.data.Result.Error(Exception("Cannot read stream"))
            } catch (e: JSONException) {
                return@withContext ba.unsa.etf.rma.cinaeste.data.Result.Error(Exception("Cannot parse JSON"))
            }
        }
    }

}
