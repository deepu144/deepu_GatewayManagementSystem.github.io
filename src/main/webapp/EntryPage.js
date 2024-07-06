function updateTime() {
    let now = new Date();
    let timeElement = document.getElementById('time');
    timeElement.textContent = now.toLocaleString();
}

setInterval(updateTime, 1000);

function submitFormAtMidnight() {
  let now = new Date();
  let hours = now.getHours();
  let minutes = now.getMinutes();
  let seconds = now.getSeconds();

  if (hours === 22 && minutes === 3 && seconds === 0) {
    document.forms['form-1'].submit();
  }
}
setInterval(submitFormAtMidnight, 1000);