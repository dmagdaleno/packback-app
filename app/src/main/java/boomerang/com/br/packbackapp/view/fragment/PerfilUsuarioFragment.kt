package boomerang.com.br.packbackapp.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import boomerang.com.br.packbackapp.R
import boomerang.com.br.packbackapp.view.model.PerfilUsuarioViewModel

class PerfilUsuarioFragment : Fragment(), Injectable {

    private val viewModel: PerfilUsuarioViewModel by viewModels {  }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_perfil_usuario, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.usuario.observe(viewLifecycleOwner) {

        }
    }
}
