package com.elhady.covid19.data.repository

import androidx.annotation.MainThread
import com.elhady.covid19.data.model.NewsData
import com.prof.rssparser.Article
import com.elhady.covid19.data.model.NewsResponse
import com.elhady.covid19.utils.State
import com.elhady.covid19.utils.getPeriodWorldNews
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow

@ExperimentalCoroutinesApi
abstract class WorldNewsBoundRepository {

    fun asFlow() = flow<State<NewsResponse>> {

        // Emit Loading World News
        emit(State.loading())

        try {
            // Fetch latest data from remote
            val apiResponse = fetchFromRemote()

            if (apiResponse.isNullOrEmpty()) {
                // Something went wrong! Emit Error state.
                emit(State.error("Something went wrong! Can't get latest data."))
            } else {
                // Success
                emit(State.success(NewsResponse(data = apiResponse.map {
                    NewsData(
                        title = it.title,
                        link = it.link,
                        date = "${getPeriodWorldNews(it.pubDate!!)} | ${it.sourceName}",
                        image = it.image
                    )
                })))
            }


        } catch (e: Exception) {
            // Exception occurred! Emit error
            emit(State.error("Network error! Can't get latest data."))
            e.printStackTrace()
        }
    }

    @MainThread
    protected abstract suspend fun fetchFromRemote(): MutableList<Article>
}