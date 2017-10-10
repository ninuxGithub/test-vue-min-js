const Login ={
		template:`<div class="container">
				<div class="panel panel-header">
					<h2>密码的方式获取token</h2>
				</div>
				<div class="panel panel-body">
					<form @submit.prevent="submit">
						<div class="form-group">
							<label for="clientId" class="col-sm-2 control-label">ClientId</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="clientId" name="client_id" value="password_auth_mode" placeholder="input ClientId (客户端必须支持授权码方式)....">
							</div>
						</div>
						<div class="form-group">
							<label for="clientSecret" class="col-sm-2 control-label">ClientSecret</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="clientSecret" name="client_secret" value="123456" placeholder="input ClientSecret (客户端必须支持授权码方式)....">
							</div>
						</div>
						
						
						<div class="form-group">
							<label for="grantType" class="col-sm-2 control-label">GrantType</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="grantType" name="grant_type" value="password" readonly="readonly">
							</div>
						</div>
						<div class="form-group">
							<label for="username" class="col-sm-2 control-label">UserName</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="username" name="username" value="admin" readonly="readonly">
							</div>
						</div>
						<div class="form-group">
							<label for="password" class="col-sm-2 control-label">Password</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="password" name="password" value="password" readonly="readonly">
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<button type="submit" class="btn btn-primary">Go</button>
							</div>
						</div>
					</form>
				</div>
			</div>`,
		data () {
		    return {
		      info: {
		        client_id: 'password_auth_mode',
		        client_secret: '123456',
		        grant_type: 'password',
		        username: 'admin',
		        password: 'password'
		      },
		      error: ''
		    }
		  },
		  created() {
				this.initPage();
		  },
		  watch:{
			'$route':'initPage'
		   },
		  methods: {
		    submit () {
			      var info = {
			      	client_id: this.info.client_id,
			      	client_secret: this.info.client_secret,
			      	grant_type: this.info.grant_type,
			        username: this.info.username,
			        password: this.info.password
			      }
			      auth.login(this, info)
		    },
		    initPage(){
		    	console.dir("init page...")
		    	
		    }
		    
		    
		  }

	
}