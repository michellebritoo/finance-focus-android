package br.com.michellebrito.financefocus.di

import br.com.michellebrito.financefocus.common.data.PreferencesStorageImpl
import br.com.michellebrito.financefocus.common.domain.PreferenceStorage
import br.com.michellebrito.financefocus.goal.create.createfirststep.presentation.CreateGoalViewModel
import br.com.michellebrito.financefocus.goal.create.createsecondstep.presentation.CreateGoalSecondStepViewModel
import br.com.michellebrito.financefocus.login.data.LoginRepositoryImpl
import br.com.michellebrito.financefocus.login.domain.LoginRepository
import br.com.michellebrito.financefocus.login.presentation.LoginViewModel
import br.com.michellebrito.financefocus.signup.data.SignUpRepositoryImpl
import br.com.michellebrito.financefocus.signup.domain.SignUpRepository
import br.com.michellebrito.financefocus.signup.presentation.emailstep.SignUpEmailViewModel
import br.com.michellebrito.financefocus.signup.presentation.passwordstep.SignUpPasswordViewModel
import br.com.michellebrito.financefocus.welcome.data.WelcomeRepositoryImpl
import br.com.michellebrito.financefocus.welcome.domain.WelcomeRepository
import br.com.michellebrito.financefocus.welcome.presentation.WelcomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val AppModule = module {
    single<PreferenceStorage> { PreferencesStorageImpl(get()) }

    factory<LoginRepository> { LoginRepositoryImpl(get()) }
    factory<WelcomeRepository> { WelcomeRepositoryImpl(get()) }
    factory<SignUpRepository> { SignUpRepositoryImpl(get()) }

    viewModel { WelcomeViewModel(get()) }
    viewModel { LoginViewModel(get()) }
    viewModel { SignUpEmailViewModel() }
    viewModel { SignUpPasswordViewModel(get()) }
    viewModel { CreateGoalViewModel() }
    viewModel { CreateGoalSecondStepViewModel() }
}
