package com.example.mvvmkotlin.ui

import androidx.lifecycle.ViewModel
import com.example.mvvmkotlin.data.Quote
import com.example.mvvmkotlin.data.QuoteRepository

class QuotesViewModel(private val quoteRepository: QuoteRepository)
    : ViewModel() {

    fun getQuotes() = quoteRepository.getQuotes()

    fun addQuote(quote: Quote) = quoteRepository.addQuote(quote)
}
