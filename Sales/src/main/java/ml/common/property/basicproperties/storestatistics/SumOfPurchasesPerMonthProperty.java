package ml.common.property.basicproperties.storestatistics;

import ml.common.property.basicproperties.ABasicProperty;
import ml.deducer.deductionrules.ADeductionRule;

/**
 * This class represents the sum of purchases made in a given month
 * @author noam
 *
 */
public class SumOfPurchasesPerMonthProperty extends ABasicProperty {

	public static int goMonthesBackLimit = 6;
	
	int monthAgo;
	double sumOfPurchases;
	
	
	public SumOfPurchasesPerMonthProperty(int monthAgo, double sumOfPurchases, ADeductionRule deducer) {
		super(deducer);
		this.monthAgo = monthAgo;
		this.sumOfPurchases = sumOfPurchases;
	}
	
	public SumOfPurchasesPerMonthProperty(int monthAgo, double sumOfPurchases) {
		this.monthAgo = monthAgo;
		this.sumOfPurchases = sumOfPurchases;
	}

	public int getMonthAgo() {
		return monthAgo;
	}

	public double getSumOfPurchases() {
		return sumOfPurchases;
	}

	@Override
	public int hashCode() {
		return monthAgo + 31 * super.hashCode();
	}

	@Override
	public boolean equals(Object o) {
		return o == this || (super.equals(o) && getClass() == o.getClass() && monthAgo == ((SumOfPurchasesPerMonthProperty) o).monthAgo);
	}
	
	@Override
	public String getDescription() {
		return "The sum of purchases "
				+ (monthAgo == 0 ? "this month" : (monthAgo == 1 ? "1 month" : monthAgo + " months") + " ago") + " "
				+ "is: " + sumOfPurchases;
	}
}
