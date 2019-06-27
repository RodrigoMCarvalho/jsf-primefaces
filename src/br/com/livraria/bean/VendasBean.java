package br.com.livraria.bean;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import br.com.livraria.dao.VendasDAO;
import br.com.livraria.modelo.Vendas;

@Named
@SessionScoped
public class VendasBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private VendasDAO dao;

	public List<Vendas> getVendas() {
		List<Vendas> list = dao.buscaVendas();
		for (Vendas vendas : list) {
			System.out.println(vendas.getLivro());
		}
		return list;
	}
	
	public BarChartModel getVendasModel() {
		List<Vendas> vendas = getVendas();
	    BarChartModel model = new BarChartModel();
	    model.setAnimate(true); //adiciona uma animação ao carregar a página
	    
	    ChartSeries chartVendas = new ChartSeries();
	    chartVendas.setLabel("Vendas 2019");
	    for (Vendas venda : vendas) {
	    	chartVendas.set(venda.getLivro().getTitulo(), venda.getQuantidade());
		}
	    model.addSeries(chartVendas);
	    return model;
	}
}
