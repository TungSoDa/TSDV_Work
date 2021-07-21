export interface ExamResult {
  examResultID: number,
  examID: number,
  contestantUsername: string,
  testMark: number,
  selectedAnswers: string,
  isDeleted: boolean
}
