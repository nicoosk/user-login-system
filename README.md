# user-login-system
##### Proyecto creado para usarse repetidas veces para crear inicios de sesión básicos.

## Instalación de la librería
Hay ciertos pasos que debes seguir para poder utilizar esta herramienta en tu proyecto personal:
1. Crea un paquete en tu proyecto donde almacenes este repositorio de forma que sea accesible.
2. Dirigete al paquete o directorio donde quieres guardar el proyecto, y clónalo de la siguiente forma: 
``git clone https://github.com/nicoosk/user-login-system``
**Ten en cuenta que este comando clona el repositorio en tu directorio actual**
3. Disfruta de la herramienta que he creado para ti.


##¿Por dónde empezar?
Al estar escrito en Java puro, hay métodos y constructores flexibles. Puedes crear instancias sobre las clases que necesites ocupar y ocupar las distintas variedades de constructores que ofrecen.

## Clases y sus constructores
Existen (hasta el momento) solo 3 clases disponibles:
- Connector
- Login
- Register

## Clase Connector
Esta clase es la principal y fundamental para que el ambiente de trabajo sea fluido. Necesitas crear una instancia de esta clase o heredar de la misma. Aquí te presento los diferentes constructores:

	public Connector(){ 
	}

	public Connector(String url){
		this.url = url;
	}

	public Connector(String user, String pass){
		this.user = user;
		this.pass = pass;
	}

	public Connector(String url, String user, String pass){
		this.url = url;
		this.user = user;
		this.pass = pass;
	}
Cada uno de estos constructores puede ser explicado por su misma estructura, pero aquí va un ejemplo:

`Connector myConnector = new Connector("jdbc:mysql://localhost:3306","myUser","myPass");`

Cabe destacar que el usuario y la contraseña que instancies en el objeto tipo Connector es **EXCLUSIVAMENTE** de la base de datos, no del usuario que deseas registrar o loggear.

**Actualmente estoy trabajando en una implementación de encriptación de constraseñas.**

## Clase Login
Esta clase, como su nombre lo indica, se encarga del loggeo del usuario. También cuenta con sus propios constructores:

	public Login(){
	}

	public Login(Connection c){
		this.c = c;
	}

	public Login(String user, String pass){
		this.user = user;
		this.pass = pass;
	}

	public Login(Connection c, String user, String pass){
		this.c = c;
		this.user = user;
		this.pass = pass;
	}

En esta ocasión, los campos *user* y *pass* deben ser los datos del usuario que deseas loggear o registrar, **NO** los de la base de datos.

La creación del objeto tipo Login sería algo como esto:
`Login myLogin = new Login("userToLogin", "passToLogin");`

Para utilizar el constructor con la variable tipo Connection, debemos primero establecer la conexión con la base de datos. [Ver más sobre cómo establecer una conexión](#método-getconnection)

## Clase Register
Esta clase sigue el mismo patrón de constructores que la [clase Login](#clase-login). De igual forma muestro el resumen de constructores:

	public Register(){
	}

	public Register(Connection c){
		this.c = c;
	}

	public Register(String user, String pass){
		this.user = user;
		this.pass = pass;
	}

	public Register(Connection c, String user, String pass){
		this.c = c;
		this.user = user;
		this.pass = pass;
	}

El método de creación de instancias sería el mismo que con el Login

# Uso correcto de la clase Connector
Al implementar esta pequeña librería, debemos tener en cuenta que valores que entran, valores que salen. Un ejemplo de esto sería el método getConnection de la clase [Connector](#clase-connector)

## Método getConnection()
Para implementar este método, primero debemos crear el objeto o instancia de la clase Connector. Ya que el método devuelve un valor de tipo Connection (diferente a Connector), debemos guardar este valor para poder manejar la instancia de la base de datos.

#### Crear conexión con el método getConnection()
Para crear correctamente una conexión, debemos guardar la conexión en una variable tipo Connection de la siguiente forma:

		Connector myConnector = new Connector("jdbc:mysql://localhost:3306","myUser","myPass");
		Connection c = null;
		
		try {
			c = myConnector.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

Es importante destacar que la ejecución del método *getConnection()* debe ser rodeada de un bloque **try-catch**, ya que este método al momento de cerrarse la conexión si ocurre una excepción, tira la excepción de tipo SQLException. Esto último lo podemos ver en el siguiente bloque de código:

	public Connection getConnection() throws SQLException {
		try{
			// hace algo
		} catch (SQLException e) {
			System.out.println("Error while trying to connect to database: " + e);
			c.close(); // <-- AQUÍ
			System.out.println("Connection " +c.getMetaData().getConnection() + "closed.");
		}
		return c;
	}

# Cómo usar la conexión establecida
Ahora que ya tenemos la conexión establecida, podemos empezar a ocuparla en los distintos métodos de cada clase. Pero primero veámos un poco de la clase Login y su método principal.

# Método logWithUser()
Este método es parte de la clase [Login](#clase-login), y tiene diferentes formas de ejecutarse:

**Ejecución sin parámetros:**

	public boolean logWithUser() {
		// do this
	}

**Con objeto tipo Connection:**

	public boolean logWithUser(@NotNull Connection c) {
		//do that
	}

**Con usuario y contraseña**

	public boolean logWithUser(@NotNull String user, @NotNull String pass) {
		//do something
	}

**Con variable tipo Connection, usuario y contraseña:**

	public boolean logWithUser(@NotNull Connection c, @NotNull String user, @NotNull String pass) {
		//doing it again
	}

Todas estas posibilidades están en armonía con los tipos de constructores que existen para cada clase. **Este patrón se repite para todas las clases**

## Cómo manejar el método logWithUser()
Como se puso observar, este método es de tipo *boolean*, por lo tanto, al ejecutarse el método nos indica si el usuario que intentamos loggear realmente se encontraba en la base de datos.

	public boolean logWithUser() {
		// do something ...
		return isUserInDB;
	}

Si devuelve *false*, el usuario no se encuentra en la base de datos (debe registrarse). Si devuelve *true*, entonces el usuario si se encuentra y puede tener acceso a lo que sea que estés construyendo.

# Método registerUser()
Este método es parte de la clase [Register](#clase-register) y tiene las mismas formas de implementarse que el método anterior.

###¿Qué lo diferencia de logWithUser()?
A diferencia del método anterior que trabaja con booleanos, éste método trabaja con enteros, siendo estos el número de filas afectadas (debería ser 1 en la mayoría de casos controlados). 

	public int registerUser(){
		//do this
		return rowsAffected;
	}

Pensaba en crear una forma de ingresar muchos usuarios de una vez, pero debido al contexto en que esto se utilizaría, lo veo totalmente innecesario. De igual manera, ¡Déjame saber tus ideas sobre eso!

#Fin del README
Gracias por pasarte por aquí. Espero que si en algún momento necesitas o te gustaría jugar con este proyecto, este README te venga de maravilla para poder empezar con esta pequeña pero útil herramienta. [Volver al inicio](#user-login-system)
