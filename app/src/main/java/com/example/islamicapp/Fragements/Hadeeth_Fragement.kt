package com.example.islamicapp.Fragements

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.islamicapp.Adapters.CustomAdapterHadeth
import com.example.islamicapp.Data.Constants
import com.example.islamicapp.Data.DataClassHadeth
import com.example.islamicapp.Data.listhadeth
import com.example.islamicapp.activities.HadethContantActivity
import com.example.islamy_project.R
import com.example.islamy_project.databinding.FragmentHadethBinding


class Hadeeth_Fragement : Fragment() {

    lateinit var adapter: CustomAdapterHadeth
    lateinit var ViewBinding : FragmentHadethBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        ViewBinding = FragmentHadethBinding.inflate(inflater,container,false)

        return ViewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // readfile()

        adapter = CustomAdapterHadeth(listhadeth)

        adapter.OnHadethClickListner = object : CustomAdapterHadeth.OnItemClickListner {
            override fun OnItemClick(position: Int, items: String) {

                var Intent = Intent(activity, HadethContantActivity::class.java)

                Intent.putExtra(Constants.EXTRA_HADETH_POSITION,position)
                Intent.putExtra(Constants.EXTRA_HADETH_NAME, items)

                startActivity(Intent)
            }
        }

        ViewBinding.recyclerview.adapter = adapter
    }

    val allAhadeth= mutableListOf<DataClassHadeth>()
    fun readfile()
    {

        var fileName="ahadeth.txt"
        var filcontant=activity?.assets?.open(fileName)?.bufferedReader().use {
            it?.readText()
        }
        if (filcontant==null) return
        val ahadethContant=filcontant.trim().split("#")
        ahadethContant.forEach{singleHadethContent->

            val title =singleHadethContent.trim().substringBefore('\n')
            val content =singleHadethContent.trim().substringAfter('\n')

            Log.e("title",title)
            Log.e("content",content)
            val hadeth=DataClassHadeth(title, content)
            allAhadeth.add(hadeth)


        }

    }

}



