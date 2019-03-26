var path = $('#path').val();
$(function(){
	$('#loginForm').bootstrapValidator({
        message: '输入不正确',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok light-green',
            invalid: 'glyphicon glyphicon-remove light-red',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
        	username: {
                validators: {
                    notEmpty: {
                        message: '请输入用户名'
                    },
			        regexp: {
			        	regexp: /^[a-zA-Z0-9_]+$/,
			        	message: '用户名或密码输入错误'
			        }
                }
            },
            password: {
                validators: {
                    notEmpty: {
                        message: '请输入登录密码'
                    } 
                }
            }
/*            verificationcode: {
                validators: {
                    notEmpty: {
                        message: '请输入验证码'
                    } 
                }
            }*/
        },
        submitHandler: function(validator, form, submitButton) {
        	
    		var username = $("#username").val();
    		var password = $("#password").val();
    		var verificationcode = $("#verificationcode").val();
			var data={'username':username,'password':password,'verificationcode':verificationcode};
			
			$.ajax({
				type : "post",
				url : ACTIVITI.CONFIG.contextRoot+"/login/login",
				dataType : "json",
				data:data,
				success : function(data) {
					console.log(data.success == true)
					if(data.success == true){
						location.href =path+'/page/view/index/index.jsp';
//						location.href = path+'/page/view/processList/process.jsp';
					}
					else if(data.errorCode == false){
			        	validator.updateStatus("username", 'INVALID', "regexp");
					}
					$('#submit').removeAttr("disabled");
				}});
			
        }
    });
	
	

});