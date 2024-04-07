package br.com.michellebrito.financefocus.di

import br.com.michellebrito.financefocus.goal.create.createfirststep.presentation.CreateGoalViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { CreateGoalViewModel() }
}