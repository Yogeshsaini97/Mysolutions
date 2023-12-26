<%@ include file="/init.jsp"%>
 
  <div class="container-fluid bg-login">
    <div class="bg-signup d-none d-md-block"></div>
    <div class="bg-login-screen-1"></div>
    <div class="bg-login-screen-2"></div>

    <div class="login-screen">
      <div class="container-fluid position-relative z-index-2">
        <div class="row">
          <div class="col-md-3">
            <div class="left-login-screen-bg">
              <h1 class="font-50">Thanks!</h1>
              <p class="mb-0 mt-3">Didnt receive the link?</p>
              <p><a href="javascript:void(0)" class="position-relative z-index-4">Resend in 4m 59s</a>
                <img src="/o/stcs-theme/images/arrow-up.svg" alt="img" />
              </p>
            </div>
          </div>
          <div class="col-md-9">
            <div class="right-login-screen-bg">
              <div class="font-10">
                <p><liferay-ui:message key="success-page-m1" /></p>

                  <p class="mb-40"><liferay-ui:message key="success-page-m2" /></p>
                  <p>Click below to explore the marketplace.</p>
                </div>
                <a href="#" class="btn btn-light btn-lg"><span class="text-default mr-4">Explore now</span><img
                  src="/o/stcs-theme/images/btn-light-arrow.svg" alt="img" /></a>

             
            </div>
          </div>
        </div>
      </div>
    </div>

  </div>