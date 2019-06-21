package br.com.livraria.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import br.com.livraria.dao.LivroDAO;
import br.com.livraria.modelo.Livro;
import br.com.livraria.modelo.Vendas;

@Named
@SessionScoped
public class VendasBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private LivroDAO dao;

	public List<Vendas> getVendas(long seed) {
		List<Vendas> vendas = new ArrayList<>();
		List<Livro> livros = dao.listaTodos();
		
		Random random = new Random(seed);
		
		for (Livro livro : livros) {
			int quantidade = random.nextInt(500);
			vendas.add(new Vendas(livro, quantidade));
		}
		return vendas;
	}
	
	public BarChartModel getVendasModel() {
		List<Vendas> vendas = getVendas(1234);
	    BarChartModel model = new BarChartModel();
	    model.setAnimate(true); //adiciona uma anima��o ao carregar a p�gina
	    
	    ChartSeries chartVendas = new ChartSeries();
	    chartVendas.setLabel("Vendas 2019");
	    for (Vendas venda : vendas) {
	    	chartVendas.set(venda.getLivro().getTitulo(), venda.getQuantidade());
		}
	    
	    vendas = getVendas(4321);
	    ChartSeries chartVendas2018 = new ChartSeries();
	    chartVendas2018.setLabel("Vendas 2018");
	    for (Vendas venda : vendas) {
	    	chartVendas2018.set(venda.getLivro().getTitulo(), venda.getQuantidade());
		}
	    model.addSeries(chartVendas);
	    model.addSeries(chartVendas2018);
	    return model;
	}
}
