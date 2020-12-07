<script>
		function AddAccount() {
			var token = $("meta[name='_csrf']").attr("content");
			var header = $("meta[name='_csrf_header']").attr("content");
			console.log(token,header)
			let newAccount = new Object();
			newAccount.username = $("#username").val();
			newAccount.password = $("#password").val();			
			newAccount.password = $("#name").val();
			newAccount.password = $("#email").val();
			newAccount.password = $("#address").val();
			newAccount.password = $("#email").val();
			newAccount.password = $("#email").val();

			$.ajax({
				url: `/admin/account/add`,
				type: 'POST',
				dataType: "json",
				contentType: "application/json",
				data: JSON.stringify(newAccount),
				beforeSend: function (xhr) {
					xhr.setRequestHeader(header, token);
				},
				success: function (data, status, xhr) {
					 try {
                         alert(data.msg);
                         window.location.href = `/admin/account/list`;
                     } catch (error) { }

				},
				error: function (request, status, error) {
					  try {
						  Swal.fire({
							  icon: 'error',
							  title: 'Oops...',
							  text: request.responseJSON.msg,
							})
                      } catch (error) { }
                  }
				
			});
		}

	</script>