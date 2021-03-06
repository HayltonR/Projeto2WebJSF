/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.edu.converters;

import br.com.modeljpa.jpa.EntityManagerUtil;
import br.com.modeljpa.modelo.Produto;
import java.io.Serializable;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Haylton
 */
@FacesConverter(value = "converterProduto")
public class ConverterProduto implements Converter, Serializable{

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if(string == null || string.equals("Selecione um registro")){
            return null;
        }else{
            return EntityManagerUtil.getEntityManager().find(Produto.class, Integer.parseInt(string));
        }
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if(o == null){
            return null;
        }else{
            Produto obj = (Produto) o;
            return obj.getId().toString();
        }
    }
    
}
