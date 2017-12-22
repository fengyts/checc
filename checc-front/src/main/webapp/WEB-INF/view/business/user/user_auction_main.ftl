
<style>
	iframe{
		/**
		width: 1237px;
		*/
		min-height: 50px;
	}
</style>
<#-- 我参与的竞拍 -->
<div class="uc_auct_box">
	<div class="uc_auct_data">
		
		<div class="uc_auct_box_t">
			<div class="uc_auct_t">
				<span>我参与的竞拍</span>
				(<span id="my_auct_num">12</span>)
			</div>
		</div>
		
		<div class="uc_auct_data_list">
			<iframe id="auct_data_list" frameborder=0 scrolling=no height="80" src="${domain}/user/bis/ucAuctionList/auction">
			</iframe>
		</div>
		
	</div>
</div>

<#-- 我参与的兑换 -->
<div class="uc_exchange_box">
	<div class="uc_exchange_data">
		
		<div class="uc_auct_box_t">
			<div class="uc_auct_t">
				<span>我兑换的商品</span>
				(<span id="my_exchange_num">12</span>)
			</div>
		</div>
		
		<div class="uc_auct_data_list">
			<iframe id="exchange_data_list" frameborder=0 scrolling=no height="80" src="${domain}/user/bis/ucAuctionList/exchange">
			</iframe>
		</div>
		
	</div>
</div>

<script type="text/javascript">
	var _ifh = $("#auct_data_list").height();
	<#--
	
	function reinitIframe(){
		try{
			var iframe = document.getElementsByTagName("iframe");
			for(var i =0;i<iframe.length;i++){
				var dHeight = iframe[i].contentWindow.document.documentElement.scrollHeight;
				iframe[i].height = dHeight;
				iframe[i].width = $(iframe[i]).parent().width();
			}
			/**
			var iframe = document.getElementById("auct_data_list");
			//var bHeight = iframe.contentWindow.document.body.scrollHeight;
			var dHeight = iframe.contentWindow.document.documentElement.scrollHeight;
			//var height = Math.max(bHeight, dHeight);
			iframe.height = dHeight;
			iframe.width = $("#auct_data_list").parent().width();
			*/
		}catch (ex){}
	}
	window.setInterval("reinitIframe()", 200);
	-->
</script>


