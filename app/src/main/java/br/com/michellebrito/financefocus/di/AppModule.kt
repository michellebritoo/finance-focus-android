package br.com.michellebrito.financefocus.di

import br.com.michellebrito.financefocus.common.data.PreferencesStorageImpl
import br.com.michellebrito.financefocus.common.data.retrofit.RetrofitInstance
import br.com.michellebrito.financefocus.common.domain.PreferenceStorage
import br.com.michellebrito.financefocus.goal.create.data.CreateGoalClient
import br.com.michellebrito.financefocus.goal.create.data.CreateGoalRepositoryImpl
import br.com.michellebrito.financefocus.goal.create.domain.CreateGoalRepository
import br.com.michellebrito.financefocus.goal.create.presentation.firststep.CreateGoalViewModel
import br.com.michellebrito.financefocus.goal.create.presentation.secondtep.CreateGoalSecondStepViewModel
import br.com.michellebrito.financefocus.home.data.HomeClient
import br.com.michellebrito.financefocus.home.data.HomeRepositoryImpl
import br.com.michellebrito.financefocus.home.domain.HomeRepository
import br.com.michellebrito.financefocus.home.presentation.HomeViewModel
import br.com.michellebrito.financefocus.login.data.LoginRepositoryImpl
import br.com.michellebrito.financefocus.login.domain.LoginRepository
import br.com.michellebrito.financefocus.login.presentation.LoginViewModel
import br.com.michellebrito.financefocus.passwordrecovery.data.PasswordRepositoryImpl
import br.com.michellebrito.financefocus.passwordrecovery.domain.PasswordRecoveryRepository
import br.com.michellebrito.financefocus.passwordrecovery.presentation.PasswordRecoveryViewModel
import br.com.michellebrito.financefocus.profile.data.ProfileClient
import br.com.michellebrito.financefocus.profile.data.ProfileRepositoryImpl
import br.com.michellebrito.financefocus.profile.domain.ProfileRepository
import br.com.michellebrito.financefocus.profile.presentation.ProfileFragmentViewModel
import br.com.michellebrito.financefocus.rates.calculate.data.CalculateRatesClient
import br.com.michellebrito.financefocus.rates.calculate.data.CalculateRatesRepositoryImpl
import br.com.michellebrito.financefocus.rates.calculate.domain.CalculateRatesRepository
import br.com.michellebrito.financefocus.rates.calculate.presentation.CalculateRatesViewModel
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

    factory { RetrofitInstance.getInstance().create(HomeClient::class.java) }
    factory { RetrofitInstance.getInstance().create(ProfileClient::class.java) }
    factory { RetrofitInstance.getInstance().create(CalculateRatesClient::class.java) }
    factory { RetrofitInstance.getInstance().create(CreateGoalClient::class.java) }

    factory<LoginRepository> { LoginRepositoryImpl(get()) }
    factory<WelcomeRepository> { WelcomeRepositoryImpl(get()) }
    factory<HomeRepository> { HomeRepositoryImpl(get(), get()) }
    factory<CalculateRatesRepository> { CalculateRatesRepositoryImpl(get(), get()) }
    factory<SignUpRepository> { SignUpRepositoryImpl(get()) }
    factory<PasswordRecoveryRepository> { PasswordRepositoryImpl() }
    factory<ProfileRepository> { ProfileRepositoryImpl(get(), get()) }
    factory<CreateGoalRepository> { CreateGoalRepositoryImpl(get(), get()) }

    viewModel { WelcomeViewModel(get()) }
    viewModel { LoginViewModel(get()) }
    viewModel { PasswordRecoveryViewModel(get()) }
    viewModel { SignUpEmailViewModel() }
    viewModel { SignUpPasswordViewModel(get()) }
    viewModel { HomeViewModel(get()) }
    viewModel { CalculateRatesViewModel(get()) }
    viewModel { CreateGoalViewModel() }
    viewModel { CreateGoalSecondStepViewModel(get()) }
    viewModel { ProfileFragmentViewModel(get()) }
}
