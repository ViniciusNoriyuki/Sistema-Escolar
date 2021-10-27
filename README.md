# Sistema-Escolar

API Rest desenvolvida em linguagem Java, com utilização de Autenticação e Autorização com Tokens JWT e sistema de envio de email com SMTP do Google.
Funcionalidades:

- Atribuido automaticamente perfil de ADMIN ao criar Professores e USER ao criar Alunos
- CRUD de disciplinas 
  - Cada disciplina possui seu professor e vários alunos
  - Apenas professores podem criar, alterar e atribuir disciplinas a alunos e professores
- CRUD de alunos
  - Cada aluno possui suas notas e disciplinas 
  - Alunos visualizam apenas suas próprias notas e disciplinas
  - Apenas professor podem criar e alterar alunos
- CRUD de professores 
  - Cada professor possui sua disciplina
  - Apenas professor podem criar e alterar professores
- CRUD de notas (apenas professores)
