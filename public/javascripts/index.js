$(window).load(preparePage);

function preparePage() {
  $(".logo").addClass("animated bounceInUp");
  $("#welcomeButtonContainer").addClass("animated bounceInUp");
  $("#calendar").addClass("animated bounceInUp");
  $("#logInButton").click(onLogInButtonClicked);
  $("#signUpButton").click(onSignUpButtonClicked);
  prepareCalendar();
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

function prepareCalendar() {
	$('#calendar').fullCalendar({
		header: {
			left: 'prev,next today',
			center: 'title',
			right: 'month,basicWeek,basicDay'
		},
		defaultDate: '2015-02-12',
		editable: true,
		eventLimit: true, 
		eventColor: "#9b59b6",
		dayClick: function(date, jsEvent, view) {
			$(".fc-selected-day").removeClass("fc-selected-day");
			$(this).addClass("fc-selected-day");
			$(".webui-popover").remove();
			$(this).webuiPopover({title: 'New Event', 
								  animation: 'pop',
								  cache: false,
								  closeable: true,
								  content: 'Title: <input data-event-date="' + date + '" id="newEventTitle"><br><br><a href="javascript:void(0)" id="submitNewEvent">Create</a>'});
			$("#submitNewEvent").click(onSubmitNewEventClicked);
		},
		events: [
			{
				title: 'All Day Event',
				start: '2015-05-13'
			},
			{
				title: 'Long Event',
				start: '2015-02-07',
				end: '2015-02-10'
			},
			{
				id: 999,
				title: 'Repeating Event',
				start: '2015-02-09T16:00:00'
			},
			{
				id: 999,
				title: 'Repeating Event',
				start: '2015-02-16T16:00:00'
			},
			{
				id: 999,
				title: 'Repeating Event',
				start: '2015-05-16T16:00:00'
			},
			{
				title: 'Conference',
				start: '2015-02-11',
				end: '2015-02-13'
			},
			{
				title: 'Meeting',
				start: '2015-02-12T10:30:00',
				end: '2015-02-12T12:30:00'
			},
			{
				title: 'Lunch',
				start: '2015-02-12T12:00:00'
			},
			{
				title: 'Meeting',
				start: '2015-02-12T14:30:00'
			},
			{
				title: 'Happy Hour',
				start: '2015-02-12T17:30:00'
			},
			{
				title: 'Happy Hour',
				start: '2015-05-10T17:30:00'
			},
			{
				title: 'Dinner',
				start: '2015-02-12T20:00:00'
			},
			{
				title: 'Birthday Party',
				start: '2015-02-13T07:00:00'
			},
			{
				title: 'Bowling Party',
				start: '2015-05-07T07:00:00'
			}
		]
	});
}

function onSubmitNewEventClicked(ev) {
	var eventInput = $("#newEventTitle");
	$('#calendar').fullCalendar.events.push({title: $(eventInput).val(), start: $(eventInput).data('event-date')})
}