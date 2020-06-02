package io.github.prabhuomkar.torchexpo.ui.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import io.github.prabhuomkar.torchexpo.BuildConfig
import io.github.prabhuomkar.torchexpo.R

class AboutFragment : Fragment() {

    companion object {
        fun newInstance() =
            AboutFragment()
    }

    private lateinit var viewModel: AboutViewModel
    private lateinit var versionTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.about_fragment, container, false)
        versionTextView = view.findViewById<TextView>(R.id.versionTextView)
        versionTextView.text = "v${BuildConfig.VERSION_NAME}"
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(AboutViewModel::class.java)
        // TODO: Use the ViewModel
    }
}
