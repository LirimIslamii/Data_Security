public class Person{
	private String Modulus;
	private String Exponent;
	private String P;
	private String Q;
	private String DP;
	private String DQ;
	private String InverseQ;
	private String D;
	
	public Person() {
		
	}

public Person(String Modulus, String Exponent, String P, String Q, String DP, String DQ, String InverseQ, String D) {
	this.Modulus = Modulus;
	this.Exponent = Exponent;
	this.P = P;
	this.Q = Q;
	this.DP = DP;
	this.DQ = DQ;
	this.InverseQ = InverseQ;
	this.D = D;
}
public Person(String Modulus, String Exponent) {
	this.Modulus = Modulus;
	this.Exponent = Exponent;
}

public String getModulus() {
	return Modulus;
}

public void setModulus(String modulus) {
	Modulus = modulus;
}

public String getExponent() {
	return Exponent;
}

public void setExponent(String exponent) {
	Exponent = exponent;
}

public String getP() {
	return P;
}

public void setP(String p) {
	P = p;
}

public String getQ() {
	return Q;
}

public void setQ(String q) {
	Q = q;
}

public String getDP() {
	return DP;
}

public void setDP(String dP) {
	DP = dP;
}

public String getDQ() {
	return DQ;
}

public void setDQ(String dQ) {
	DQ = dQ;
}

public String getInverseQ() {
	return InverseQ;
}

public void setInverseQ(String inverseQ) {
	InverseQ = inverseQ;
}

public String getD() {
	return D;
}

public void setD(String d) {
	D = d;
}

}
