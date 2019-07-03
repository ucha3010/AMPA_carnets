<HTML>
<HEAD>
<TITLE>Verificaci&oacute;n carnet</TITLE>
<style>
	body { 
		background-color: #AEFF3A;
	}
	.centrado{
		text-align:center;
	}
	.sombra{
		text-shadow: 1px 1px blue;
	}
	.colorRojo{
		color: red;
	}
	.tamanio150porciento{
		font-size: 150%;
	}
	.colorAzul{
		color:blue;
	}
</style>
</HEAD>
<BODY>
	<p class="centrado tamanio150porciento">Verificaci&oacute;n de carnet</p>
	<p class="centrado tamanio150porciento colorRojo sombra">AMPA Colegio El Valle Sanchinarro</p>
	<br/>
	<p class="centrado">Familia: <span class="colorAzul"><?php echo $_GET["familia"] ?></span></p>
	<p class="centrado">Curso de validez del carnet: : <span class="colorAzul"><?php echo $_GET["curso"] ?></span></p>
	<p class="centrado">Fecha de vencimiento: : <span class="colorAzul"><?php echo $_GET["vencimiento"] ?></span></p>
	<br/>
	<center><a href="http://ampacolegioelvallesanchinarro.com/">Ir a web AMPA</a></center>
</BODY>
</HTML>