package br.com.michellebrito.financefocus.di

import br.com.michellebrito.financefocus.goal.create.createfirststep.presentation.CreateGoalViewModel
import br.com.michellebrito.financefocus.goal.create.createsecondstep.presentation.CreateGoalSecondStepViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val AppModule = module {
    viewModel { CreateGoalViewModel() }
    viewModel { CreateGoalSecondStepViewModel() }
}