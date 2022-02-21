package com.bolsadeideas.springboot.datajpa.app.view.pdf;

import java.awt.Color;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.bolsadeideas.springboot.datajpa.app.models.entity.Factura;
import com.bolsadeideas.springboot.datajpa.app.models.entity.ItemFactura;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

@Component("factura/ver")
public class FacturaPdfView extends AbstractPdfView {
	
	
	@SuppressWarnings("unused")
	@Autowired
	private MessageSource messageSource;
	
	@SuppressWarnings("unused")
	@Autowired
	private LocaleResolver localeResolver;

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		Factura factura = (Factura) model.get("factura");
		
	//	Locale locale = localeResolver.resolveLocale(request);
		
	//	MessageSourceAccessor mensajes = getMessageSourceAccessor();

		PdfPTable tabla = new PdfPTable(1);
		tabla.setSpacingAfter(20);

		PdfPCell cell = null;

		cell = new PdfPCell(new Phrase("Datos del Cliente"));
		cell.setBackgroundColor(new Color(184, 218, 255));
		cell.setPadding(8f);

		tabla.addCell(cell);

		tabla.addCell(factura.getCliente().getNombre() + " " + factura.getCliente().getApellido());
		tabla.addCell(factura.getCliente().getEmail());

		PdfPTable tabla2 = new PdfPTable(1);
		tabla2.setSpacingAfter(20);

		cell = new PdfPCell(new Phrase("Datos de la Factura"));
		cell.setBackgroundColor(new Color(195, 230, 203));
		cell.setPadding(8f);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);

		tabla2.addCell(cell);
		tabla2.addCell("Folio: " + factura.getId());
		tabla2.addCell("Descripcion: " + factura.getDescripcion());
		tabla2.addCell("Fecha: " + factura.getCreateAt());

		PdfPTable tabla3 = new PdfPTable(4);
		tabla3.setWidths(new float[] { 3.5f, 1, 1, 1.5f });

		tabla3.addCell("Producto");
		tabla3.addCell("Precio");
		tabla3.addCell("Cantidad");
		tabla3.addCell("Total");
		for (ItemFactura item : factura.getItems()) {
			cell = new PdfPCell(new Phrase("Datos de la Factura"));

			tabla3.addCell(item.getProducto().getNombre());
			cell = new PdfPCell(new Phrase(item.getProducto().getPrecio().toString()));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			tabla3.addCell(cell);
			cell = new PdfPCell(new Phrase(item.getCantidad().toString()));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			tabla3.addCell(cell);
			cell = new PdfPCell(new Phrase(item.calcularImporte().toString()));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			tabla3.addCell(cell);

		}
		cell = new PdfPCell(new Phrase("Total: "));
		cell.setColspan(3);
		cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
		tabla3.addCell(cell);
		cell = new PdfPCell(new Phrase(factura.getTotal().toString()));
		cell.setBackgroundColor(new Color(195, 230, 203));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		tabla3.addCell(cell);

		document.add(tabla);
		document.add(tabla2);
		document.add(tabla3);

	}

}
