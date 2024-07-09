package br.com.michellebrito.financefocus.goal.create.presentation.secondtep

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.michellebrito.financefocus.MainActivity
import br.com.michellebrito.financefocus.R
import br.com.michellebrito.financefocus.databinding.FragmentCreateGoalSecondStepBinding
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.snackbar.Snackbar
import com.redmadrobot.inputmask.MaskedTextChangedListener
import org.koin.androidx.viewmodel.ext.android.viewModel

class CreateGoalSecondStepFragment : Fragment(R.layout.fragment_create_goal_second_step) {
    private val binding: FragmentCreateGoalSecondStepBinding by viewBinding()
    private val viewModel: CreateGoalSecondStepViewModel by viewModel()
    private var frequencyMonth = true
    private var relativeValue = true

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = arguments?.let {
            CreateGoalSecondStepFragmentArgs.fromBundle(it)
        }
        args?.let {
            viewModel.onStart(args.name, args.description, args.totalValue)
        }
    }

    override fun onResume() {
        super.onResume()
        setupListeners()
        setupView()
        setupObservers()
    }

    private fun setupListeners() = with(binding) {
        topBar.setNavigationOnClickListener { findNavController().popBackStack() }
        btnCreateGoal.setOnClickListener { onContinueButtonClicked() }
        btnFrequencyMonth.setOnClickListener { frequencyMonth = true }
        btnFrequencyWeek.setOnClickListener { frequencyMonth = false }
        btnValueRelative.setOnClickListener { relativeValue = true }
        btnValueFixed.setOnClickListener { relativeValue = false }
        etDateInit.addTextChangedListener { tilDateInit.error = null }
        etDateFinish.addTextChangedListener { tilDateFinish.error = null }
    }

    private fun setupView() = with(binding) {
        etDateInit.apply {
            addTextChangedListener(
                MaskedTextChangedListener("[00]/[00]/[0000]", this)
            )
        }
        etDateFinish.apply {
            addTextChangedListener(
                MaskedTextChangedListener("[00]/[00]/[0000]", this)
            )
        }
    }

    private fun setupObservers() {
        viewModel.viewState.observe(viewLifecycleOwner) { state ->
            when(state) {
                is CreateGoalSecondStepEvent.ShowLoading -> showLoading()
                is CreateGoalSecondStepEvent.HideLoading -> hideLoading()
                is CreateGoalSecondStepEvent.InitDateError -> showInitDateError()
                is CreateGoalSecondStepEvent.FinishDateError -> showFinishDateError()
            }
        }
    }

    private fun onContinueButtonClicked() = with(binding) {
        if (etDateInit.text?.isNotEmpty() == true && etDateFinish.text?.isNotEmpty() == true) {
            viewModel.onContinueButtonClicked(
                gradualProgress = relativeValue,
                monthFrequency = frequencyMonth,
                initDate = etDateInit.text.toString(),
                finishDate = etDateFinish.text.toString()
            )
        } else {
            showError()
        }
    }

    private fun showError() {
        this@CreateGoalSecondStepFragment.view?.let {
            Snackbar.make(it, R.string.create_goals_generic_error, Snackbar.LENGTH_LONG).show()
        }
    }

    private fun showLoading() = (requireActivity() as MainActivity).showLoading()

    private fun hideLoading() = (requireActivity() as MainActivity).hideLoading()

    private fun showInitDateError() {
        binding.tilDateInit.error = getString(R.string.create_goals_date_error)
    }

    private fun showFinishDateError() {
        binding.tilDateFinish.error = getString(R.string.create_goals_date_error)
    }

    private fun goToGoalsList() {
        findNavController().navigate(R.id.createGoalSecondStepFragmentToListGoalFragment)
    }
}
