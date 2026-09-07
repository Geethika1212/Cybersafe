let password =
document.getElementById("password");

if(password)
{
password.addEventListener("keyup", function(){

let p = password.value;
let strength =
document.getElementById("strength");

if(p.length < 6)
{
strength.innerHTML = "Weak 🔴";
}
else if(p.length < 10)
{
strength.innerHTML = "Medium 🟡";
}
else
{
strength.innerHTML = "Strong 🟢";
}

});
}

function showOTP()
{
document.getElementById("otpSection")
.style.display = "block";
}

function verifyOTP()
{
let otp =
document.getElementById("otp").value;

if(otp=="1234")
{
window.location="dashboard.html";
}
else
{
alert("Wrong OTP");
}
}

let completed = 0;

function completeModule(num)
{
completed++;

let percent = (completed/4)*100;

document.getElementById("bar")
.style.width = percent + "%";

localStorage.setItem(
"progress",
percent
);
}

function submitQuiz()
{
    let score = 0;

    let answers =
    document.querySelectorAll(
    'input[type="radio"]:checked'
    );

    answers.forEach(function(ans){
        score += parseInt(ans.value);
    });

    let level;

    if(score >= 8)
    {
        level = "Cyber Smart";
    }
    else if(score >= 5)
    {
        level = "Intermediate";
    }
    else
    {
        level = "Beginner";
    }

    // Keep localStorage for result.html
    localStorage.setItem("score", score);

    // Send values to servlet
    document.getElementById("score").value = score;
    document.getElementById("total").value = 10;
    document.getElementById("level").value = level;

    // Submit form to QuizServlet
    document.getElementById("quizForm").submit();
}