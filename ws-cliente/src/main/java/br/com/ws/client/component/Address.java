package br.com.ws.client.component;

import java.util.List;

import br.com.ws.client.model.AddressModel;


public interface Address {

	public List<AddressModel> getAddress() throws IllegalArgumentException;
}
