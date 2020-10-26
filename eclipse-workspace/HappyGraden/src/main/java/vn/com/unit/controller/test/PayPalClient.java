package vn.com.unit.controller.test;

import com.paypal.core.PayPalEnvironment;
import com.paypal.core.PayPalHttpClient;

public class PayPalClient {

	/**
	 * Set up PayPal SDK environment with PayPal access credentials. This sample
	 * uses SandboxEnvironment. In production, use LiveEnvironment.
	 */
	private PayPalEnvironment environment = new PayPalEnvironment.Sandbox(
			System.getProperty("PAYPAL-CLIENT-ID") != null ? System.getProperty("PAYPAL-CLIENT-ID")
					: "AcqqPmYk26IAvOp3-_78qBZflIpy5J3T_ilj4sT7JF3N5d8TTKZKLRQbz_iEMWCQ8U6DpEHfuXW9Zq3E",
			System.getProperty("PAYPAL-CLIENT-SECRET") != null ? System.getProperty("PAYPAL_CLIENT_SECRET")
					: "ELelwuejdWVN8uVb8rOSP5c-vkGlZzZk-SPPXKtyvskhLn2hhoR95T4GeVnlg6OAaJrI_rlOnBms2985");
	/**
	 * Returns PayPal HTTP client instance in an environment with access
	 * credentials. Use this instance to invoke PayPal APIs, provided the
	 * credentials have access.
	 */
	PayPalHttpClient client = new PayPalHttpClient(environment);

	/**
	 * Method to get client object
	 *
	 * @return PayPalHttpClient client
	 */
	public PayPalHttpClient client() {
		return this.client;
	}
}