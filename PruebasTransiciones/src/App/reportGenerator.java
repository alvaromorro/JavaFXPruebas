package App;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;
import java.awt.Color;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

import net.sf.dynamicreports.adhoc.AdhocManager;
import net.sf.dynamicreports.adhoc.configuration.AdhocColumn;
import net.sf.dynamicreports.adhoc.configuration.AdhocConfiguration;
import net.sf.dynamicreports.adhoc.configuration.AdhocReport;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.ReportBuilder;
import net.sf.dynamicreports.report.builder.column.PercentageColumnBuilder;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.adhoc.report.DefaultAdhocReportCustomizer;
import net.sf.dynamicreports.examples.Templates;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.report.definition.datatype.DRIDataType;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;

public class reportGenerator {

	public reportGenerator() {
		build();
	}

	private void build() {
		AdhocConfiguration configuration = new AdhocConfiguration();

		AdhocReport report = new AdhocReport();

		configuration.setReport(report);

		AdhocColumn columnaI = new AdhocColumn();
		columnaI.setName("item");
		report.addColumn(columnaI);
		
		AdhocColumn columnaQ = new AdhocColumn();
		columnaQ.setName("quantity");
		report.addColumn(columnaQ);

		try {

			JasperReportBuilder reportBuilder = AdhocManager.createReport(configuration.getReport(),
					new ReportCustomizer());

			reportBuilder.setDataSource(createDataSource());

			reportBuilder.show();
			

		} catch (DRException e) {

			e.printStackTrace();

		}

	}

	private JRDataSource createDataSource() {

		DRDataSource dataSource = new DRDataSource("item", "orderdate", "quantity", "unitprice");

		for (int i = 0; i < 15; i++) {
			dataSource.add("Book", new Date(), (int) (Math.random() * 10) + 1, new BigDecimal(Math.random() * 100 + 1));
		}
		for (int i = 0; i < 20; i++) {
			dataSource.add("PDA", new Date(), (int) (Math.random() * 10) + 1, new BigDecimal(Math.random() * 100 + 1));
		}
		return dataSource;
	}

	private class ReportCustomizer extends DefaultAdhocReportCustomizer {

		public void customize(ReportBuilder<?> report, AdhocReport adhocReport) throws DRException {

			super.customize(report, adhocReport);
			// default report values
			report.setTemplate(Templates.reportTemplate);
			report.title(Templates.createTitleComponent("Informe"));
			// a fixed page footer that user cannot change, this customization
			// is not stored in the xml file
			report.pageFooter(Templates.footerComponent);

		}

		@Override
		protected DRIDataType<?, ?> getFieldType(String name) {
			if (name.equals("item")) {
				return type.stringType();
			}
			if (name.equals("orderdate")) {
				return type.dateType();
			}
			if (name.equals("quantity")) {
				return type.integerType();
			}
			if (name.equals("unitprice")) {
				return type.bigDecimalType();
			}
			return super.getFieldType(name);
		}

		@Override
		protected String getFieldLabel(String name) {
			if (name.equals("item")) {
				return "Item";
			}
			if (name.equals("orderdate")) {
				return "Order date";
			}
			if (name.equals("quantity")) {
				return "Quantity";
			}
			if (name.equals("unitprice")) {
				return "Unit price";
			}
			return name;

		}

	}

}
