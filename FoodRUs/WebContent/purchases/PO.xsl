<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	version="1.0">
	<xsl:template match="/">
		<html>
			<head>
				<title>Purchase Order | Foods R Us</title>
			</head>
			<body>
				<xsl:apply-templates />
			</body>
		</html>
	</xsl:template>

	<xsl:template match="order">
		<h1>Details of Order:</h1>
		Order Number:
		<xsl:value-of select="@id" />
		<br />
		Order Date:
		<xsl:value-of select="@submitted" />
		<br />
		<xsl:apply-templates select="customer" />
		<xsl:apply-templates select="items" />
		<br />
		Subtotal:
		$<xsl:value-of select="./total" />
		<br />
		Shipping:
		$<xsl:value-of select="./shipping" />
		<br />
		HST:
		$<xsl:value-of select="./HST" />
		<br />
		<b>Grand Total</b>
		:
		$<xsl:value-of select="./grandTotal" />
		<br />
		<br />
		<!-- <xsl:apply-templates /> -->
	</xsl:template>
	<xsl:template match="customer">
		Customer Name:
		<xsl:value-of select="./name" />
		<br />
		Customer Account:
		<xsl:value-of select="@account" />
		<br />
	</xsl:template>
	<xsl:template match="items">
		<p>
			<h2>Ordered items:</h2>
			<table border="1">
				<tr>
					<td>Product ID</td>
					<td>Name</td>
					<td>Price</td>
					<td>Quantity</td>
					<td>Extended</td>
				</tr>
				<xsl:for-each select="item">
					<xsl:sort select="name" />
					<tr>
						<td>
							<xsl:value-of select="@number" />
						</td>
						<td>
							<xsl:value-of select="name" />
						</td>
						<td>
							$<xsl:value-of select="price" />
						</td>
						<td>
							<xsl:value-of select="quantity" />
						</td>
						<td>
							$<xsl:value-of select="extended" />
						</td>
					</tr>
				</xsl:for-each>
			</table>
		</p>
	</xsl:template>

</xsl:stylesheet>