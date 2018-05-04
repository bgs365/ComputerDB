/**
 * 
 */
$(document)
		.ready(
				function() {
					$('#Add')
							.click(
									function(e) {
										var computerName = $('#computerName')
												.val();
										var introduced = $('#introduced').val();
										var discontinued = $('#discontinued')
												.val();
										var companyId = $('#companyId').val();

										$(".error").remove();

										if (computerName.length < 5) {
											$('#computerName')
													.after(
															'<span  class="error" style="color:blue;font-weight:bold"> This field must contain must than 5 carraters </span>');

											e.preventDefault();
										}
										
										if(computerName.search("<") >= 0  || computerName.search("/") >= 0 || computerName.search(">") >= 0 || computerName.search("*") >= 0 ){
											$('#computerName')
											.after(
													'<span  class="error" style="color:blue;font-weight:bold"> Illegal carracter detected </span>');

												e.preventDefault();
										}

										if (introduced == ''
												&& discontinued != '') {
											$('#introduced')
													.after(
															'<span class="error" style="color:blue;font-weight:bold">You must enter and introduced date</span>');

											e.preventDefault();
										}

										if (discontinued != ''
												&& introduced != '') {
											if (discontinued < introduced) {
												$('#introduced')
														.after(
																'<span class="error" style="color:blue;font-weight:bold">Dicounted date must be after introduced date</span>');
												$('#discontinued')
														.after(
																'<span class="error" style="color:blue;font-weight:bold">Dicounted date must be after introduced date</span>');

												e.preventDefault();
											}
										}

									});

				})