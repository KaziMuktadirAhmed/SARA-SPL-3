import { NewHome } from "@/components/component/new-home";

export default async function Home() {
  return (
    <main className="flex flex-col items-center justify-between min-h-screen p-24">
      <NewHome />
    </main>
  );
}
