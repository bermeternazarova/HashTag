package com.example.hashtag

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.addTextChangedListener
import com.example.hashtag.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val adapterHashTag :AdapterHashTag = AdapterHashTag(this::click)
   // private val hashtag=ArrayList<String>()
    private val hashTag = hashSetOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
        initListeners()
        showHashtag()
    }

    private fun initView() {
        binding.rvMain.adapter =adapterHashTag
    }

    private fun initListeners() {
        binding.btnSend.setOnClickListener {
            hashTag.addAll(findHashtag(binding.etEnter.text.toString()))
            binding.etEnter.text.clear()
        }
    }

    private fun showHashtag() {
        binding.etEnter.addTextChangedListener {
            adapterHashTag.addData(findInSaved(binding.etEnter.text.toString()))
        }
    }

    private fun findHashtag(text: String): ArrayList<String> {
        val result = arrayListOf<String>()
        val word = text.split(" ")
        for (i in word) {
            if (i.startsWith("#")) {
                 result.add(i)}}
        return result
    }

    private fun findInSaved(text: String): ArrayList<String> {
        val result = arrayListOf<String>()
        val onlineTag = findHashtag(text)
        for (tag in onlineTag) {
            for (savedTag in hashTag) {
                if (savedTag.contains(tag) && savedTag != tag)
                    result.add(savedTag)}}
        return result
    }

    private fun click (newHashtag: String) {
        val word = binding.etEnter.text.toString()
        val divText = word.split(" ").toMutableList()
        val tagIndex = findIndex(divText, word)
        divText[tagIndex] = newHashtag
        binding.etEnter.setText(divText.joinToString(" "))
        binding.etEnter.setSelection(binding.etEnter.text.length)
    }

    private fun findIndex(divText: MutableList<String>, word: String): Int {
        for (i in findHashtag(word)) {
            if (!hashTag.contains(i))
                return divText.indexOf(i)
        }
        return 0
    }
}
