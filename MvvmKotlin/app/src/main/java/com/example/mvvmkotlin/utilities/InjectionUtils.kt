package com.example.mvvmkotlin.utilities

import com.example.mvvmkotlin.data.FakeDatabase
import com.example.mvvmkotlin.data.QuoteRepository
import com.example.mvvmkotlin.ui.QuotesViewModelFactory

object InjectionUtils {

    fun provideQuotesViewModelFactory(): QuotesViewModelFactory {
        val quoteRepository = QuoteRepository.getInstance(FakeDatabase.getInstance().quoteDao)
        return QuotesViewModelFactory(quoteRepository)
    }
}