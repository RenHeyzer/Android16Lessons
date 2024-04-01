package com.geeks.androidlesson7

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import com.geeks.androidlesson7.databinding.FragmentCatalogBinding

class CatalogFragment : Fragment(), OnItemClickListener {

    private var _binding: FragmentCatalogBinding? = null
    private val binding get() = _binding!!
    private val catalogAdapter = CatalogAdapter(
        catalogList = CatalogData.catalogList,
        onItemClickListener = this
    )

    /**
     * Фрагмент привязывается к Activity и получает Context
     */
    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    /**
     *  Фрагмент создается
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    /**
     * View создаюстя
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Инициализация nullable binding'a
        _binding = FragmentCatalogBinding.inflate(inflater, container, false)
        // Возращен
        return binding.root
    }

    /**
     * View уже созданы и готовы к работе
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        navigateToAddProduct()
        acceptProduct()
    }

    private fun initialize() {
        binding.rvCatalog.adapter = catalogAdapter
    }

    private fun navigateToAddProduct() {
        binding.fabAddProduct.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .add(R.id.fragment_container, AddProductFragment())
                .addToBackStack(CATALOG_TAG)
                .commit()
        }
    }

    private fun acceptProduct() {
        setFragmentResultListener(AddProductFragment.ADD_PRODUCT_REQUEST_KEY) { requestKey, bundle ->
            if (requestKey == AddProductFragment.ADD_PRODUCT_REQUEST_KEY) {
                bundle.getParcelable<Product>(PRODUCT_KEY)?.let { product ->
                    catalogAdapter.addProduct(product)
                }
            }
        }
    }

    /**
     * Фрагмент запускается
     */
    override fun onStart() {
        super.onStart()
    }

    /**
     * Фрагмент работает и готов для взаимодействия
     */
    override fun onResume() {
        super.onResume()
    }

    /**
     * Фрагмент приостановлен
     */
    override fun onPause() {
        super.onPause()
    }

    /**
     * Фрагмент остановлен
     */
    override fun onStop() {
        super.onStop()
    }

    override fun onItemClick(product: Product) {
        val detailsFragment = DetailsFragment().apply {
            arguments = Bundle().apply {
                putParcelable(PRODUCT_KEY, product)
            }
        }
        parentFragmentManager.beginTransaction()
            .add(R.id.fragment_container, detailsFragment)
            .addToBackStack(CATALOG_TAG)
            .commit()
    }

    override fun onLongClick(position: Int) {
        catalogAdapter.removeProduct(position)
    }

    /**
     * View уничтожаются
     */
    override fun onDestroyView() {
        super.onDestroyView()
        // Очистка viewBinding
        _binding = null
    }

    /**
     * Фрагмент уничтожается
     */
    override fun onDestroy() {
        super.onDestroy()
    }

    /**
     * Фрагмент отвязывается от Activity
     */
    override fun onDetach() {
        super.onDetach()
    }

    companion object {
        const val CATALOG_TAG = "Catalog"
        const val PRODUCT_KEY = "product"
    }
}