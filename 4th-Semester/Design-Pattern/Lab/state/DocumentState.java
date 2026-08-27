public interface DocumentState {

  void edit(Document document);

  void submit(Document document);

  void verify(Document document);

  void approve(Document document);

  void reject(Document document, String reason);

  void returnForCorrection(Document document);

  void issue(Document document);

  void archive(Document document);

  String getStateName();
}