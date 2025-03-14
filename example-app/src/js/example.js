import { ReaderInterface } from 'color-meter-plugin';

window.testEcho = () => {
    const inputValue = document.getElementById("echoInput").value;
    ReaderInterface.echo({ value: inputValue })
}
