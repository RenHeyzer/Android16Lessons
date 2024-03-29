package com.geeks.androidlesson7

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.geeks.androidlesson7.databinding.FragmentCatalogBinding

class CatalogFragment : Fragment() {

    private var _binding: FragmentCatalogBinding? = null
    private val binding get() = _binding!!

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
        navigateToDetails()
    }

    private fun navigateToDetails() = with(binding) {
        btnToDetails.setOnClickListener {
            val text = etInput.text.toString().trim()
            if (text.isEmpty()) {
                etInput.error = getString(R.string.fill_field)
            } else {
                val bundle = Bundle()
                bundle.putString(TEXT_KEY, text)
                val detailsFragment = DetailsFragment().apply {
                    arguments = bundle
                }
                parentFragmentManager.beginTransaction()
                    .add(R.id.fragment_container, detailsFragment)
                    .addToBackStack(CATALOG_TAG)
                    .commit()
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
        const val TEXT_KEY = "text"
    }
}