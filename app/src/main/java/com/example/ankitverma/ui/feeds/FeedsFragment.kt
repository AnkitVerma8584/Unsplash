package com.example.ankitverma.ui.feeds

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.ankitverma.databinding.FragmentFeedsBinding
import com.example.ankitverma.ui.feeds.adapter.FeedsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FeedsFragment : Fragment() {

    private var _binding: FragmentFeedsBinding? = null
    private val binding get() = _binding!!
    private val feedsViewModel: FeedsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFeedsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = FeedsAdapter()

        binding.apply {
            feedsRecyclerView.adapter = adapter
            feedsViewModel.feeds.observe(viewLifecycleOwner) {
                feedSwipeRefreshLayout.isRefreshing = false
                adapter.submitList(it)
                shimmerFeeds.stopShimmer()
                shimmerFeeds.visibility = View.GONE

                if (it.isEmpty()) Toast.makeText(
                    requireContext(), "Unable to refresh feeds. Check Internet", Toast.LENGTH_SHORT
                ).show()
            }
            feedSwipeRefreshLayout.setOnRefreshListener {
                feedsViewModel.getFeeds()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}