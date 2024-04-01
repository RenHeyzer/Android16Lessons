package com.geeks.androidlesson7

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import com.geeks.androidlesson7.databinding.FragmentAddProductBinding

class AddProductFragment : Fragment() {

    private var _binding: FragmentAddProductBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddProductBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        goBack()
        addProduct()
    }

    private fun goBack() {
        binding.iconBack.setOnClickListener {
            Toast.makeText(requireContext(), "click", Toast.LENGTH_SHORT).show()
            parentFragmentManager.popBackStack()
        }
    }

    private fun addProduct() = with(binding) {
        btnSubmit.setOnClickListener {
            val price = etPrice.text.toString().trim()
            val firm = etFirm.text.toString().trim()
            val body = etBody.text.toString().trim()

            if (price.isEmpty()) {
                etPrice.error = getString(R.string.fill_field_erorr_message)
            } else etPrice.error = null

            if (firm.isEmpty()) {
                etFirm.error = getString(R.string.fill_field_erorr_message)
            } else etFirm.error = null

            if (body.isEmpty()) {
                etBody.error = getString(R.string.fill_field_erorr_message)
            } else etBody.error = null

            if (price.isNotEmpty() && firm.isNotEmpty() && body.isNotEmpty()) {
                val bundle = Bundle().apply {
                    val product = Product(
                        coverImage = R.drawable.product_cover_img,
                        price = price.toDouble(),
                        firm = firm,
                        body = body
                    )
                    putParcelable(CatalogFragment.PRODUCT_KEY, product)
                }
                setFragmentResult(ADD_PRODUCT_REQUEST_KEY, bundle)
                parentFragmentManager.popBackStack()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val ADD_PRODUCT_REQUEST_KEY = "AddProduct"
    }
}