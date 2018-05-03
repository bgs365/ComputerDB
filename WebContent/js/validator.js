/**
 * 
 */
$(document)
		.ready(
				function() {
					$('#addComputerForm')
							.submit(
									function(e) {
										e.preventDefault();
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
															'<span  class="error" style="color:blue;font-weight:bold">This field is required</span>');
										}

										if (introduced == ""
												&& discontinued != "") {
											$('#introduced')
													.after(
															'<span class="error" style="color:blue;font-weight:bold">You must enter and introduced date</span>');
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
											}
										}

									});

				})