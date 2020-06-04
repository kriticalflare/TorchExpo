package io.github.prabhuomkar.torchexpo.ui.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import io.github.prabhuomkar.torchexpo.BuildConfig
import io.github.prabhuomkar.torchexpo.R
import io.github.prabhuomkar.torchexpo.databinding.AboutFragmentBinding

class AboutFragment : Fragment() {

    companion object {
        fun newInstance() =
            AboutFragment()
    }

    private lateinit var viewModel: AboutViewModel
    private var _binding: AboutFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = AboutFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.versionTextView.text =
            requireContext().getString(R.string.app_version, BuildConfig.VERSION_NAME)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(AboutViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
