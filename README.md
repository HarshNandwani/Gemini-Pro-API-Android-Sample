This codebase serves as a demonstration of the implementation of Gemini Pro Gen-AI using the "Google AI client SDK".

# Implementation details
**This sample code repository utilizes Gemini Pro APIs with clean architecture principles, Examine the code to gain insights into the implementation details.**

If you want to get started quickly follow the below steps: <br>

**Add SDK dependency**: Add the following in module-level (app) ```build.gradle.kts``` file
```
implementation("com.google.ai.client.generativeai:generativeai:0.1.1")
```
<br>

**Generate your API Key** <br>
You need to generate an API Key from Google AI Studio. [Follow this steps](https://ai.google.dev/tutorials/setup) to generate your key.
<br> <br>

**Create your model** <br>
Create your GenAI Model object, pass the API key generated in above step here. 
```
val geminiProModel = GenerativeModel(
    modelName = "gemini-pro",
    apiKey = <YOUR_API_KEY>
)
```

Browse through the sample code to: <br>
- Secure your API key with gradle-secrets-plugin, this way the apiKey would reside in your local.properties file and won't be included in your Version Control System. <br>
- See how to implemented this as a datasource in data layer.
<br> <br>

**Input the prompt to the model** <br>
Single prompt with no context:
```
val prompt = "Write a story about a magic backpack."
val response = geminiProModel.generateContent(prompt)
print(response.text)
```
Starting a conversion:
```
val chat = geminiProModel.startChat()
val response = chat.sendMessage(message)
print(response.text)
```
<br>

**Note:** If you intend to clone and experiment with the provided sample code, it is necessary to generate an API key in "Google AI Studio." Subsequently, place this key in the local.properties file under the variable name "apiKey".
<br>

# Contact me :email:
- LinkedIn: [Harsh Nandwani](https://www.linkedin.com/in/harsh-nandwani/)
- E-Mail: developer.harsh.nandwani@gmail.com
