$(window).load(preparePage);

function preparePage() {
  $(".logo").addClass("animated bounceInUp");
  $("#welcomeButtonContainer").addClass("animated bounceInUp");
  $("#calendar").addClass("animated bounceInUp");
  $("#logInButton").click(onLogInButtonClicked);
  $("#signUpButton").click(onSignUpButtonClicked);
}

function onLogInButtonClicked() {
	if (!$("#logInForm").is(":visible")) {
		$("#logInForm").slideDown();
		$("#signUpForm").slideUp();
		$("#logInButton").fadeOut();
		$("#signUpButton").fadeOut();
	} else {
		$("#logInForm").slideUp();
		$("#logInButton").fadeIn();
		$("#signUpButton").fadeIn();
	}
}

function onSignUpButtonClicked() {
	if (!$("#signUpForm").is(":visible")) {
		$("#signUpForm").slideDown();
		$("#logInForm").slideUp();
		$("#logInButton").fadeOut();
		$("#signUpButton").fadeOut();
	} else {
		$("#signUpForm").slideUp();
		$("#logInButton").fadeIn();
		$("#signUpButton").fadeIn();
	}
}