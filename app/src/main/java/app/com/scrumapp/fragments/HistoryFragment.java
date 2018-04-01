package app.com.scrumapp.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import app.com.scrumapp.R;
import app.com.scrumapp.activities.historiausuario.HistoriaUsuarioActivity;
import app.com.scrumapp.adapters.HistoryAdapter;
import app.com.scrumapp.models.Proyecto;


/**
 * A simple {@link Fragment} subclass.
 */
public class HistoryFragment extends Fragment implements AdapterView.OnItemClickListener{

    private ListView listViewHistory;
    private HistoryAdapter adapterHistoryView;

    //lista de nuestro modelo fruit
    private List<Proyecto> proyectos;

    public HistoryFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, container, false);

        this.proyectos = getAllProyectos();

        this.adapterHistoryView = new HistoryAdapter(getContext(), R.layout.list_view_item_history, proyectos);

        this.listViewHistory = (ListView) view.findViewById(R.id.listView);

        this.listViewHistory.setOnItemClickListener(this);

        this.listViewHistory.setAdapter(adapterHistoryView);

        registerForContextMenu(this.listViewHistory);

        return view;

    }

    private List<Proyecto> getAllProyectos() {
        List<Proyecto> list = new ArrayList<Proyecto>() {{
            add(new Proyecto("HU 1", R.mipmap.ic_launcher, "pepito 1"));
            add(new Proyecto("HU 2", R.mipmap.ic_launcher, "pepito 2"));
            add(new Proyecto("HU 3", R.mipmap.ic_launcher, "pepito 3"));
            add(new Proyecto("HU 4", R.mipmap.ic_launcher, "pepito 4"));
            add(new Proyecto("HU 5", R.mipmap.ic_launcher, "pepito 5"));
            add(new Proyecto("HU 6", R.mipmap.ic_launcher, "pepito 6"));
            add(new Proyecto("HU 7", R.mipmap.ic_launcher, "pepito 7"));
        }};
        return list;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        this.clickHistory(proyectos.get(position));
    }

    private void clickHistory(Proyecto proyecto) {
        // Diferenciamos entre las frutas conocidas y desconocidas
        if(proyecto.getOrigin().equals("Unknown")) {
            Toast.makeText(getContext(), "Sorry, we don't have many info about " + proyecto.getName(), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "Historia " + proyecto.getOrigin() + " is " + proyecto.getName(), Toast.LENGTH_SHORT).show();
            Intent intento= new Intent(this.getContext(),HistoriaUsuarioActivity.class);
            startActivity(intento);
        }
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        // Inflamos el context menu con nuestro layout
        MenuInflater inflater = getActivity().getMenuInflater();
        // Antes de inflar, le añadimos el header dependiendo del objeto que se pinche
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        menu.setHeaderTitle(this.proyectos.get(info.position).getName());
        // Inflamos
        inflater.inflate(R.menu.context_menu_proyectos, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        // Obtener info en el context menu del objeto que se pinche
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId()) {
            case R.id.delete_proyecto:
                this.deleteProyecto(info.position);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private void deleteProyecto(int position) {
        this.proyectos.remove(position);
        // Avisamos del cambio en ambos adapters
        this.adapterHistoryView.notifyDataSetChanged();

    }

}
