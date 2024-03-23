package com.ernestbg.phrasalverbs

import android.app.Application
import com.ernestbg.phrasalverbs.data.PhrasalVerbsDatabase
import com.ernestbg.phrasalverbs.model.PhrasalVerb
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONArray

@HiltAndroidApp
class PhrasalVerbsApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        loadJsonData()
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun loadJsonData() {
        GlobalScope.launch {
            val resourceId =
                R.raw.phrasal_verbs // Reemplaza "your_json_file_name" con el nombre de tu archivo JSON
            val jsonString =
                applicationContext.resources.openRawResource(resourceId).bufferedReader()
                    .use { it.readText() }
            val jsonArray = JSONArray(jsonString)

            val phrasalVerbsList = parseJsonArray(jsonArray)
            PhrasalVerbsDatabase.getDatabase(applicationContext).phrasalVerbsDao()
                .insertAll(phrasalVerbsList)
        }
    }

    private fun parseJsonArray(jsonArray: JSONArray): List<PhrasalVerb> {
        val resultList = mutableListOf<PhrasalVerb>()
        for (i in 0 until jsonArray.length()) {
            val jsonPhrasalVerb = jsonArray.getJSONObject(i)
            val headword = jsonPhrasalVerb.getString("headword")
            val definition = jsonPhrasalVerb.getString("definition")
            val guideword = jsonPhrasalVerb.getString("guideword")
            val example = jsonPhrasalVerb.getString("example")

            val phrasalVerb = PhrasalVerb(
                headword = headword,
                definition = definition,
                guideword = guideword,
                example = example
            )
            resultList.add(phrasalVerb)
        }
        return resultList
    }
}