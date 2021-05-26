package br.ifce.mvc;
import java.util.HashMap;
import java.util.Map;

public abstract class Visao<T> {
	public Visao(T controlador) {
		this.controlador = controlador;
		attributes = new HashMap<String, Object>();
	}
	
	public abstract String render();

	public T controlador;
	public Map<String, Object> attributes;
}
