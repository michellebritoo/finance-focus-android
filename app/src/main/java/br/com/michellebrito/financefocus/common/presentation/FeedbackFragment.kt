package br.com.michellebrito.financefocus.common.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import br.com.michellebrito.financefocus.R
import br.com.michellebrito.financefocus.databinding.FragmentFeedbackBinding
import by.kirich1409.viewbindingdelegate.viewBinding

class FeedbackFragment : Fragment(R.layout.fragment_feedback) {
    private val binding: FragmentFeedbackBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args: FeedbackFragmentArgs by navArgs()
        val title = args.title
        val description = args.description
        val buttonText = args.buttonText
        val onCloseAction = args.onCloseAction
        val type = args.type

        binding.customFeedback.setTitle(title)
        binding.customFeedback.setDescription(description)
        binding.customFeedback.setButton(buttonText)
        binding.customFeedback.setType(type)

        binding.customFeedback.setOnButtonClickListener {
            findNavController().navigate(onCloseAction)
        }
    }

}