document.getElementById('loginButton').addEventListener('click', () => {
            let invalidPopUpBox = document.getElementById('invalidPopUpBox');

            let validateLoginInformation = () => {
                let userName = document.getElementById('userName').value;
                let password = document.getElementById('loginPassword').value;
                if (userName !== "" && password !== "")
                {
                    document.getElementById('studentLogin').submit();
                } 
                else 
                {
                    event.preventDefault(); 
                    invalidPopUpBox.classList.remove('visibility');
                    setTimeout(vanishInvalidBox, 2000);
                }
            }

            let vanishInvalidBox = () => {
                invalidPopUpBox.classList.add('visibility');
            }

            validateLoginInformation();
        });
        document.getElementById('studentLogin').event.preventDefault();