package com.geeks.androidlesson7

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.geeks.androidlesson7.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        acceptArguments()
        goBack()
    }

    private fun acceptArguments() = with(binding) {
        arguments?.let {
            it.getParcelable<Product>(CatalogFragment.PRODUCT_KEY)?.let { product ->
                ivProductCover.setImageResource(product.coverImage)
                tvProductPrice.text = product.price.toString()
                tvProductFirm.text = product.firm
                tvProductBody.text = product.body
            }
        }
    }

    private fun goBack() {
        binding.iconBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}